package investments.commands;

import java.util.List;

import investments.InvestmentEngineException;
import investments.InvestmentOptions;
import investments.NoInvestmentOptionsAddedYetException;
import investments.Pair;
import investments.Prediction;

public class Predictions implements Command {

	private InvestmentOptions allInvestments;

	public Predictions(InvestmentOptions allInv) {
		this.allInvestments = allInv;
	}

	public String execute(String command) throws InvestmentEngineException {
		//predictions 2000 5
		
		if(command.split(" ").length < 3) {
			throw new MissingCommandArgumentsException();
		}
		
		if(allInvestments.getSize()==0) {
			throw new NoInvestmentOptionsAddedYetException();
		}

		int years = Integer.parseInt(command.split(" ")[2]);

		Prediction prediction = allInvestments.getBestPrediction(years);

		StringBuilder sBuilder = new StringBuilder();

		List<Pair> pairs = prediction.getPairs();
		for (int i = 0; i < pairs.size(); i++) {

			String year = pairs.get(i).getYear() > 1 ? "years" : "year";
			sBuilder.append(allInvestments.getIndex(pairs.get(i).getInvestment()).getName() + " for "
					+ pairs.get(i).getYear() + " " + year);
			if (i < pairs.size() - 1) {
				sBuilder.append(" and then after that in ");
			}
		}

		return "Invest in: " + sBuilder.toString();
	}
}
