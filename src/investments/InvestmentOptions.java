package investments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InvestmentOptions {
	private List<InvestmentOption> availableInvestments;

	public InvestmentOptions() {
		availableInvestments = new ArrayList<>();
	}

	public void addNewInvestment(InvestmentOption invOPt) {
		availableInvestments.add(invOPt);
	}

	public int getSize() {
		return availableInvestments.size();
	}

	public InvestmentOption getIndex(int i) {
		return availableInvestments.get(i);
	}

	public InvestmentOption getByName(String name) throws NoInvestmentWithSuchNameException {
		for (InvestmentOption opt : availableInvestments) {
			if (opt.getName().equals(name)) {
				return opt;
			}
		}

		throw new NoInvestmentWithSuchNameException();
	}


	public double[][] createRevenueMatrix(int years) {
				
		//the indices of the columns will represent the years
		int horizontalLenght = years; 
				
		//the rows will represent each revenuePercent array
		int verticalLenght = availableInvestments.size();

		double[][] revenueMatrix = new double[verticalLenght][horizontalLenght];
		
		
		for (int i = 0; i < verticalLenght; i++) { 
			int arraySize = availableInvestments.get(i).getRevenuePerCentSize();
			for (int j = 0; j < horizontalLenght; j++) { 
				double value;
				if (j >= arraySize) {
					value = availableInvestments.get(i).getRevenuePerCent()[arraySize - 1];
				}else {
					value = availableInvestments.get(i).getRevenuePerCent()[j];
				}
				revenueMatrix[i][j] = value;
			}
		}
	
		return revenueMatrix;
	}
	
	public List<Prediction> getAllPredictions(int years){
		double[][] revenueMatrix = createRevenueMatrix(years);

		List<Prediction> predictions = new ArrayList<>();

		int yearIndex = years - 1;
		for (int i = 0; i < revenueMatrix.length; i++) { // all investments
			for (int j = yearIndex; j >= 0; j--) { // all years
				
					int yearsLeft = years;
					
					Prediction prediction = new Prediction();
					double sum =0.0;
					int g=0;
					for(g =0;g<=j;g++) { 
						sum+=revenueMatrix[i][g];
						yearsLeft--;
					}
					Pair pair = new Pair(i,g);
					prediction.addPair(pair);
									
					
					int k= i+1; 
					while(k < revenueMatrix.length) {
						for(int w=0; yearsLeft > 0 ; w++) {
							Pair anotherPair = new Pair(k,w+1);	
							sum+=revenueMatrix[k][w];  
							yearsLeft--;							
							prediction.addPair(anotherPair);
						}
						k++;
					}
										
					if(yearsLeft==0) {
						prediction.addSum(sum);
						predictions.add(prediction);
					}
				
			}
		}

		return predictions;
	}
	
	public Prediction getBestPrediction(int years) {
		List<Prediction> predictions = getAllPredictions(years);
		
		List<Prediction> sortedPredictions = predictions.stream().sorted(new Comparator<Prediction>() {
			public int compare(Prediction p1, Prediction p2) {
				return Double.compare(p1.getSum(), p2.getSum()) * -1;
			}
		}).collect(Collectors.toList());
		
		
		return sortedPredictions.get(0);
	}
	
}
