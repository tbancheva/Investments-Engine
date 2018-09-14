package investments.commands;

import java.io.FileWriter;
import java.io.IOException;
import investments.InvestmentOptionFactory;
import investments.InvestmentOption;
import investments.InvestmentOptions;

public class AddProduct implements Command {

	private static final String TXT_FILE_EXTENSION = ".txt";
	private InvestmentOptions allInvestments;

	public AddProduct(InvestmentOptions allInv) {
		this.allInvestments = allInv;
	}

	public String execute(String command) throws CommandException{
		if(command.split(" ").length < 11) {
			throw new MissingCommandArgumentsException();
		}
		
		addNewInvestment(command);
		return "Investment option added successfully";
	}
		
	public void addNewInvestment(String command) {
		InvestmentOption investmentOption = new InvestmentOptionFactory(command.substring(12)).createInvestment();
		allInvestments.addNewInvestment(investmentOption);
		writeToFile(investmentOption, command);
	}
	
	public void writeToFile(InvestmentOption invOption, String command) {
		try (FileWriter file = new FileWriter(invOption.getName() + TXT_FILE_EXTENSION)) {
			file.write(command.substring(12));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
