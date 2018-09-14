import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import investments.InvestmentOption;
import investments.InvestmentOptionFactory;
import investments.InvestmentOptions;
import investments.Prediction;

public class InvestmentOptionsTest {

	private static InvestmentOptions availableInvestments;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		availableInvestments = new InvestmentOptions();

		String command = "type: bank, name: Unicredit Bulbank, risk: 0.3%,"
				+ " revenue: 1:4%, 3:1%, *:0.1%, inTax: 3, outTax: 5";
		String command2 = "type: bank, name: Raiffeisen Bank, risk: 0.5%,"
				+ " revenue: 1:5%, 1:2%, *:0.3%, inTax: 4, outTax: 15";

		InvestmentOption invOpt1 = new InvestmentOptionFactory(command).createInvestment();
		InvestmentOption invOpt2 = new InvestmentOptionFactory(command2).createInvestment();

		availableInvestments.addNewInvestment(invOpt1);
		availableInvestments.addNewInvestment(invOpt2);
	}

	@Test
	public void getRevenueMatrixTest() {
		double[][] expected = { { 4, 1, 1, 1, 0.1 }, { 5, 2, 0.3, 0.3, 0.3 } };
		double[][] actual = availableInvestments.createRevenueMatrix(5);

		assertEquals(2, actual.length);

		for (int i = 0; i < actual.length; i++) {
			for (int j = 0; j < actual[i].length; j++) {
				assertEquals(expected[i][j], actual[i][j], 2);
			}
		}
	}
	
	@Test
	public void getAllPredictionsTest() {
		int years =3;
		int years2 = 2;
		List<Prediction> predictions = availableInvestments.getAllPredictions(years);
		List<Prediction> predictions2 = availableInvestments.getAllPredictions(years2);
		
		assertEquals(4, predictions.size());
		assertEquals(3, predictions2.size());

	}
	
	@Test
	public void getBestPredictionTest() {
		int years =3;
		int years2 = 2;
		
		Prediction prediction = availableInvestments.getBestPrediction(years);
		Prediction prediction2 = availableInvestments.getBestPrediction(years2);
		
		assertEquals(11, prediction.getSum(),2);
		assertEquals(9, prediction2.getSum(),2);
	
	}


}
