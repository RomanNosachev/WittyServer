package com.witty_home.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.witty_home.command_module.model.CommandRepository;
import com.witty_home.module_base.BaseManager;
import com.witty_home.module_base.command.Command;
import com.witty_home.module_base.command.MacroCommand;
import com.witty_home.module_base.command.PlaybackCommand;
import com.witty_home.module_base.command.StringCommand;
import com.witty_home.module_base.command.TypedCommand;
import com.witty_home.module_base.controller.BaseController;
import com.witty_home.module_base.controller.Controller;
import com.witty_home.module_base.controller.StringController;
import com.witty_home.module_base.controller.StringRequest;
import com.witty_home.module_base.model.Model;
import com.witty_home.module_base.model.PlaybackAction;
import com.witty_home.module_base.model.PlaybackModel;
import com.witty_home.module_base.model.StringAction;
import com.witty_home.module_base.model.StringModel;
import com.witty_home.module_base.view.StringResponce;
import com.witty_home.module_base.view.StringView;
import com.witty_home.module_base.view.View;

@EnableMongoRepositories(basePackageClasses = CommandRepository.class)
@SpringBootApplication(scanBasePackages = {"com.witty_home"})
public class CoreMain 
{
	public static void main(String[] args)
	{
		SpringApplication.run(CoreMain.class, args);
		System.out.println("Done");
	}
	
	public void test()
	{
		WittyManager coreManager = new WittyManager();
		
		BaseManager<StringRequest> manager = new BaseManager<>(StringRequest.class);
		
		View<StringResponce> view = new StringView();
		Model<StringAction> model = new StringModel();
		
		Model<PlaybackAction> playModel = new PlaybackModel();
		
		System.out.println("Enter request >");
		
		String requestString;
		Scanner scanner = new Scanner(System.in);
		requestString = scanner.nextLine();
		scanner.close();
		
		/*
		 * Типы данных компонентов для спецификации
		 */
		StringRequest request = new StringRequest(requestString);
		StringAction action = new StringAction();
		StringResponce responce = new StringResponce();
		
		PlaybackAction playAction = new PlaybackAction("Shodan.mp3"); 
		
		/*
		 * Создание команды с параметрами запроса, исполнителя, действия и ответа на нее,
		 * команды тоже будут создаваться спрингом 
		 */		
		TypedCommand<StringRequest, StringAction, StringResponce> command = new StringCommand(request, action, responce, model);
		TypedCommand<StringRequest, PlaybackAction, StringResponce> playCommand = new PlaybackCommand(request, playAction, responce, playModel);
		
		List<Command<StringRequest>> commands = new ArrayList<>();
		commands.add(command);
		commands.add(playCommand);
		
		MacroCommand<StringRequest> macroCommand = new MacroCommand<StringRequest>(request, commands);
		macroCommand.execute();
		
		Controller<StringRequest, StringAction, StringResponce> controller = new StringController(model, view, command);
		//Controller<StringRequest, PlaybackAction, StringResponce> playController = new PlaybackController(playModel, view, playCommand);
		Controller<StringRequest, PlaybackAction, StringResponce> playController = new BaseController<>(playModel, view, playCommand);
		
		/*
		 * Если по другому не получится -- то это допустимый вариант, тогда вся магия связывания должна лечь на плечи Spring
		 */
		controller.setCommand(command);
		playController.setCommand(playCommand);
		
		manager.addController(controller);
		manager.addController(playController);
		//manager.addCommand(command);
		//manager.addController(controller);

		controller.handle(request);
		playController.handle(request);
	}
}
