/*
package ua.artcode.market.exclude;

import com.google.gson.Gson;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import ua.artcode.todo.dao.TodoDaoImp;
import ua.artcode.todo.server.AddTodoHandler;
import ua.artcode.todo.server.GetAllTodoHandler;
import ua.artcode.todo.server.HelloHandler;
import ua.artcode.todo.service.MainService;
import ua.artcode.todo.service.MainServiceImpl;

import java.io.File;

import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;

public class NewServer {

    public static void main(String[] args) throws Exception {

        Gson gson = new Gson();
        MainService mainService = new MainServiceImpl(new TodoDaoImp());

        String SERVER_PORT = System.getenv("5000");
        if (SERVER_PORT == null) {
            SERVER_PORT = "5000";
        }

        Server server = new Server(Integer.parseInt(SERVER_PORT));
        server.setRequestLog(
                (request, response) ->
                        System.out.println(request.toString() + "\n" + response));


        server.setErrorHandler(new ErrorHandler());

        HandlerList handlers = new HandlerList();

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{"/todo.html"});

        File resourceBase = new File(RunServer.class.getResource("/view").getFile());
        resource_handler.setResourceBase(resourceBase.getAbsolutePath());


        ContextHandler contextHandler1 = new ContextHandler();
        contextHandler1.setContextPath("/add-todo");
        contextHandler1.setHandler(new AddTodoHandler(mainService, gson));
        contextHandler1.setAllowNullPathInfo(true);

        ContextHandler contextHandler2 = new ContextHandler();
        contextHandler2.setContextPath("/hello");
        contextHandler2.setHandler(new HelloHandler());
        contextHandler2.setAllowNullPathInfo(true);

        ContextHandler contextHandler3 = new ContextHandler();
        contextHandler3.setContextPath("/get-all-todo");
        contextHandler3.setHandler(new GetAllTodoHandler(mainService, gson));
        contextHandler3.setAllowNullPathInfo(true);

        */
/*ContextHandler contextHandler4 = new ContextHandler();
        contextHandler3.setContextPath("/remove-todo");
        contextHandler3.setHandler(new RemoveTodoHandler(mainService, gson));
        contextHandler3.setAllowNullPathInfo(true);*//*


        handlers.setHandlers(new Handler[]{resource_handler, contextHandler1,
                contextHandler2, contextHandler3*/
/*, contextHandler4*//*
});

        mainService.initData();


        server.setHandler(handlers);

        server.start();
        server.join();
    }

}*/
