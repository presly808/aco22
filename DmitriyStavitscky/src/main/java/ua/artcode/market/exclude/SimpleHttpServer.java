package ua.artcode.market.exclude;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import ua.artcode.market.controller.ITerminal;
import ua.artcode.market.exceptions.AppDBExceptions;
import ua.artcode.market.exceptions.SaveBillException;
import ua.artcode.market.exceptions.TerminalExceptions;
import ua.artcode.market.exclude.Utils.ServerUtils;
import ua.artcode.market.factory.TerminalFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException, SaveBillException, AppDBExceptions, TerminalExceptions {

        ITerminal terminal = TerminalFactory.create();

        String name1 = "Dima Stavitscky";
        String name2 = "Ivan Raskolnikov";
        String name3 = "Kolia Ivanov";

        String login1 = "lDima";
        String login2 = "lIvan";
        String login3 = "lKolia";

        int pass1 = 123;
        int pass2 = 456;
        int pass3 = 789;

        terminal.getAppDB().addProductToDataBase("Milk", 100);
        terminal.getAppDB().addProductToDataBase("Apple", 70);
        terminal.getAppDB().addProductToDataBase("Meat", 10);

        terminal.addSalesman(name1, login1, pass1);
        terminal.addSalesman(name2, login2, pass2);
        terminal.addSalesman(name3, login3, pass3);

        terminal.signIn(login3, pass3);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.addProductToBill(2);
        terminal.addProductToBill(3);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.signIn(name1, pass1);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.addProductToBill(2);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.signIn(login2, pass2);
        terminal.createBill();
        terminal.addProductToBill(1);
        terminal.closeAndSaveBill();
        terminal.logOut();

        terminal.getAppDB().findSalesmanByLoginOrName(login1).setDirector(true);

        Gson gson = new Gson();

        HttpServer server = HttpServer.create(new InetSocketAddress(8009), 0);
        server.createContext("/showBill", httpExchange -> {
            OutputStream outputStream = httpExchange.getResponseBody();

            try {
                int idOfBill = Integer.parseInt(ServerUtils.getParams(httpExchange).get(0));
                String foundBill = gson.toJson(terminal.getAppDB().findBillById(idOfBill));

                httpExchange.sendResponseHeaders(200, foundBill.length());
                outputStream.write(foundBill.getBytes());

            } catch (Exception exc) {
                httpExchange.sendResponseHeaders(999, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } finally {
                outputStream.flush();
                outputStream.close();
            }

        });

        server.createContext("/signIn", httpExchange -> {
            OutputStream outputStream = httpExchange.getResponseBody();

            try {
                List<String> params = ServerUtils.getParams(httpExchange);

                String loginOrName = params.get(0);
                int pass = Integer.parseInt(params.get(1));
                terminal.signIn(loginOrName, pass);

                String outputMessage = terminal.getLoggedSalesman().getLogin() + " logged in";

                httpExchange.sendResponseHeaders(200, outputMessage.length());
                outputStream.write(outputMessage.getBytes());

            } catch (Exception exc) {
                httpExchange.sendResponseHeaders(999, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } finally {
                outputStream.flush();
                outputStream.close();
            }
        });

        server.createContext("/logOut", httpExchange -> {

            try (OutputStream outputStream = httpExchange.getResponseBody()) {
                terminal.logOut();
                String outputMessage = "you are logged out";

                httpExchange.sendResponseHeaders(200, outputMessage.length());
                outputStream.write(outputMessage.getBytes());
            }
        });

        server.createContext("/createBill", httpExchange -> {
            OutputStream outputStream = httpExchange.getResponseBody();

            try {
                terminal.createBill();
                String outputMessage = "bill created";

                httpExchange.sendResponseHeaders(200, outputMessage.length());
                outputStream.write(outputMessage.getBytes());

            } catch (TerminalExceptions exc) {
                httpExchange.sendResponseHeaders(998, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } finally {
                outputStream.flush();
                outputStream.close();
            }
        });

        server.createContext("/addProductToBill", httpExchange -> {
            OutputStream outputStream = httpExchange.getResponseBody();

            try {
                int id = Integer.parseInt(ServerUtils.getParams(httpExchange).get(0));
                terminal.addProductToBill(id);
                String outputMessage = "Added product: " + terminal.getAppDB().findProductById(id).toString();

                httpExchange.sendResponseHeaders(200, outputMessage.length());
                outputStream.write(outputMessage.getBytes());

            } catch (Exception exc) {
                httpExchange.sendResponseHeaders(997, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } finally {
                outputStream.flush();
                outputStream.close();
            }
        });

        server.createContext("/closeAndSaveBill", httpExchange -> {
            OutputStream outputStream = httpExchange.getResponseBody();

            try {
                terminal.closeAndSaveBill();

                String gsonBill = gson.toJson(terminal.getAllBills().get(terminal.getAllBills().size() - 1));
                String outputMessage = gsonBill + "\n Bill closed";

                httpExchange.sendResponseHeaders(200, outputMessage.length());
                outputStream.write(outputMessage.getBytes());

            } catch (Exception exc) {
                httpExchange.sendResponseHeaders(996, exc.getMessage().length());
                outputStream.write(exc.getMessage().getBytes());

            } finally {
                outputStream.flush();
                outputStream.close();
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server started. Connect to localhost:8009");
    }
}

/*
public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8009), 0);
        server.createContext("/test", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                String requestUrl = httpExchange.getRequestURI().toString();
                System.out.println(requestUrl);
                System.out.println("HTTP method is " + httpExchange.getRequestMethod());

                String[] params = requestUrl.split("\\?")[1].split("&");
                String name = params[0].split("=")[1];

                try(OutputStream outputStream = httpExchange.getResponseBody()) {
                    String s = "Hello " + name;
                    httpExchange.sendResponseHeaders(200, s.length());

                    outputStream.write(s.getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
            }
        });

        server.createContext("/hello", new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                if(httpExchange.getRequestMethod().equals("POST")) {
                    Scanner sc = new Scanner(httpExchange.getRequestBody());
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while((line = sc.nextLine()) != null) {
                        sb.append(line).append("\n");
                    }

                    System.out.println(sb.toString());
                }
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server started. Connect to localhost:8000/test");
    }
}
*/
/*
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Title</title>

<style>
        .data-container {
                border-style: solid;
                }

</style>
</head>
<body>

<div id="dataContainer1" class="data-container"></div>

<div id="dataContainer2" class="data-container"></div>

<script>

    window.setInterval(function(){
            document.getElementById("dataContainer1").innerHTML = new Date().getSeconds() + " seconds";
            }, 1000);


            window.setInterval(function(){
            document.getElementById("dataContainer2").innerHTML = new Date().getMinutes() + " minutes";
            }, 1000);


</script>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Terminal</title>
</head>
<body>

<script>
    var xhr = new XMLHttpRequest();
            xhr.open("POST", yourUrl, true);
            xhr.setRequestHeader('Content-Type', 'application/json');
            xhr.send(JSON.stringify({
            value: value
            }));
            xhr.onreadystatechange = function () {
            if (this.readyState != 4) return;

            if (this.status == 200) {
            var data = JSON.parse(this.responseText);

            // we get the returned data
            }

            // end of state change: it can be after some time (async)
            };

            xhr.open('GET', yourUrl, true);
            xhr.send();

</script>

</body>
</html
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Sign In</title>
</head>
<body>


<form action="/signIn" method="get">
<label>Input login or name</label>
<input login="loginOrName" type="text"/>
<label>Input password</label>
<input name="pass" type="text"/>
<input type="submit">

</form>

</body>
</htm
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>HeadName</title>
<head>
<body>
<h1>Hello</h1>
<div id="container"></div>
<button id="getDataButton"onclick="sendData()">Get Data</button>
</body>
<h1>Second body</h1>
<div id="dataContainer" ></div>
<!--
<button onclick="inputData()">Click Me</button>
        -->
<button id="clickId">Click Me</button>

<script>
    function sendData(){
            console.log(new Date());
            document.getElementById('container').innerHTML = "Button was pressed";

            document.getElementById("clickId").onclick = function () {
            inputData();
            }

            function inputData(){
            var elementDC = document.getElementById("dataContainer")
            elementDC.inerHTML = "<h1>Some title</h1>"
            document.getElementById("dataContainer").innerHTML = "<h1>Some Title</h1>"
            }

            }

</script>

</html>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Ajax</title>
</head>
<body>

<button id="showBill" onclick="showBillById()">Show Bill</button>
<h1>Bill info</h1>
<div id="billDiv"></div>
<script>

    function showBillById() {

            var xhr = new XMLHttpRquest();

            xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById("billDiv").innerHTML = xhr.responseText;
            }
            };

            xhr.open("GET", "localhost:8009/showBill?id=7", true);

            xhr.send();


            }


</script>

</body>
</html>

        package ua.artcode.market.exceptions;

public class ServerException extends Exception {
    public ServerException(String message) {
        super(message);
    }
}



package ua.artcode.market.exclude.Utils;

        import com.sun.net.httpserver.HttpExchange;
        import ua.artcode.market.exceptions.ServerException;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

public class ServerUtils {
    public static List<String> getParams(HttpExchange httpExchange) throws ServerException {
        String requestUrl = httpExchange.getRequestURI().toString();

        if (!requestUrl.contains("?")) {
            throw new ServerException("url does not have any params");
        }

        String[] params = requestUrl.split("\\?")[1].split("&");
        List<String> res = new ArrayList<>();

        Arrays.stream(params).forEach(s -> res.add(s.split("=")[1]));

        return res;
    }
}*/
