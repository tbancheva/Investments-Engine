package investments.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import investments.InvestmentOptionFactory;
import investments.InvestmentEngineException;
import investments.InvestmentOption;
import investments.InvestmentOptions;
import investments.commands.AddProduct;
import investments.commands.AddSum;
import investments.commands.Command;
import investments.commands.ExtractSum;
import investments.commands.Predictions;
import investments.commands.Start;
import investments.commands.Stop;
import investments.commands.WrongCommandNameException;

public class Terminal {

	private Map<String, Command> commands = new HashMap<>();
	private InvestmentOptions availableInvestments;
	private Client client;
	
	public Terminal(InvestmentOptions availableInvestments, Client client) {
		this.availableInvestments = availableInvestments;
		this.client = client;
		loadInvestments();
	}

	public void open() {
		fill();

		try(Scanner input = new Scanner(System.in)){
			String command = input.nextLine();
	
			while (!command.equals("end")) {
				try {
					String result = getCommandResponse(command);
					System.out.println(result);
					command = input.nextLine();
	
				} catch (InvestmentEngineException e) {
					System.out.println(e.getMessage());
					command = input.nextLine();
				}
	
			}
			
			System.out.println(client.afterFinishingInvestment());
		}
		

	}

	public String getCommandResponse(String command) throws InvestmentEngineException {
		String commandName;
			
		if(command.split(" ").length > 1 && command.split(" ")[1].equals("product")) {  // add product
			commandName = "add product";
		}else if(command.split(" ").length > 1){           // add sum/ extract sum/ predictions sum
			commandName = command.split(" ")[0];
		}else {
			commandName = command; // start/stop
		}
		
		if(!commands.containsKey(commandName)) {
			throw new WrongCommandNameException();
		}
		
		return commands.get(commandName).execute(command);
	}

	private void fill() {
		commands.put("add product", new AddProduct(availableInvestments));
		commands.put("predictions", new Predictions(availableInvestments));
		commands.put("add", new AddSum(availableInvestments, client));
		commands.put("extract", new ExtractSum(client));
		commands.put("start", new Start(client));
		commands.put("stop", new Stop(client));
	}
	
	// Upon starting the program investments data previously used is loaded
	private void loadInvestments() {
		File[] files = new File(System.getProperty("user.dir")).listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".txt");
			}
		});

		for (File file : files) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				while (br.ready()) {
					InvestmentOption investmentOption = new InvestmentOptionFactory(br.readLine()).createInvestment();
					availableInvestments.addNewInvestment(investmentOption);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		InvestmentOptions availableInvestments = new InvestmentOptions();
		Client client = new Client();
		Terminal terminal = new Terminal(availableInvestments, client);
		terminal.open();
	}

	public InvestmentOptions getAvailableInvestments() {
		return availableInvestments;
	}


}
