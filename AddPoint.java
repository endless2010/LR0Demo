public class AddPoint {
	Transform t = new Transform();

	public String[] add(String str) {

		String[] strOut1 = new String[100];
		StringBuffer[] strOut = new StringBuffer[100];
		for (int i = 0; i < strOut.length; i++) {
			strOut[i] = new StringBuffer();
		}
		String[] strIn = new String[50];
		strIn = t.transfer(str);
		int m = 0;
		for (int i = 0; i < strIn.length; i++) {

			if (!strIn.equals("")) {
				for (int j = 1; j < strIn[i].length(); j++) {
					strOut[m].append(j);
					strOut[m].append(strIn[i]);
					m++;
				}
			}
		}
		for (int k = 0; k < strOut.length; k++) {
			strOut1[k] = strOut[k].toString();
		}
		return strOut1;

	}

	public static void main(String[] args) {
		AddPoint addPoint = new AddPoint();
		String[] test = new String[100];
		String strTest = "O>S S>ABC A>a  A>BC\nB>b\nC>cd";
		test = addPoint.add(strTest);

		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
	}
}