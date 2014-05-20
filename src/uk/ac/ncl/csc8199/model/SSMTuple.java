package uk.ac.ncl.csc8199.model;

public class SSMTuple {

	public double amonut;
	public double sampleSize;
	public long SSMtimestamp;

	public double getAmonut() {
		return amonut;
	}

	public void setAmonut(double amonut) {
		this.amonut = amonut;
	}

	public double getSampleSize() {
		return sampleSize;
	}

	public void setSampleSize(double sampleSize) {
		this.sampleSize = sampleSize;
	}

	public long getSSMtimestamp() {
		return SSMtimestamp;
	}

	public void setSSMtimestamp(long sSMtimestamp) {
		SSMtimestamp = sSMtimestamp;
	}

	public SSMTuple() {
	};

	public SSMTuple(double amount, double sample, long SSMtimeStamp) {
	};

}
