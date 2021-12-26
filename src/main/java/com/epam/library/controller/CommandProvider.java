package com.epam.library.controller;

import com.epam.library.controller.impl.*;
import com.epam.library.controller.impl.go_command.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private  final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("SignIn", new SingInCommand());
        commands.put("Registration", new RegistrationCommand());
//        commands.put("UserAction", new UserCommand());
//        commands.put("AdminAction", new AdminCommand());
////        commands.put("BookAction", new SearchBookCommand());
        commands.put("GoToRegistration", new GoToRegistration());
        commands.put("GoToCatalog", new GoToCatalogCommand());
        commands.put("ShowCatalogByPage", new ShowCatalogByPageCommand());
        commands.put("SearchBooks", new SearchBookCommand());
        commands.put("UserCatalog", new UserCatalogCommand());

        commands.put("GoToOrder", new GoToOrderCommand());
        commands.put("Order", new OrderCommand());

        commands.put("GoToBookDetails", new GoToBookDetailsCommand());

        commands.put("GoToOrderUser", new GoToOrderUserCommand());
        commands.put("GoToWishBooksUserPage", new GoToWishBooksUserCommand());  //+

        commands.put("OrderCatalog", new RequestCatalogCommand());
        commands.put("GoToRequestsCatalog", new GoToRequestCatalogCommand());

        commands.put("GoToLoanCardUser", new GoToLoanCardUserCommand());
        commands.put("PersonalCard", new PersonalCardCommand());

        commands.put("GoToAddBook", new GoToAddBookPage());

        commands.put("GoToStoryLoanCardUser", new GoToStoryLoanCardCommand());


        commands.put("GoToPrivateRoom", new GoToPrivateRoomCommand());

        commands.put("UpdateUser", new UpdateUserCommand());
        commands.put("GoToUpdateUser", new GoToUpdateUserCommand());

        commands.put("GoToMainPage", new GoToMainPageCommand());


        commands.put("GoToHome", new GoToHomeCommand());        //+
        commands.put("GoToMessagePage", new GoToMessagePageCommand());
        commands.put("GoToHello", new GOTOTest());

        commands.put("GoToAdminPage", new GoToAdminPageCommand());
        commands.put("AdminPage", new AdminPageCommand());

//        commands.put("AdminPage", new AdminPageCommand());

        commands.put("GoToStatistics", new GoToStatisticLibraryCommand());
    }

    public final Command getCommand(String command) {
        System.out.println("Command/" + command);
        return commands.get(command);
    }
}
