public class LR0 {

	int m = 0;
	TerminalAndNon ter = new TerminalAndNon();

	AddPoint addPoint = new AddPoint();
	StringBuffer[] strBuf = new StringBuffer[50];

	public LR0() {

		for (int i = 0; i < strBuf.length; i++) {
			strBuf[i] = new StringBuffer();
		}
	}

	public void project(String str, String arg) {// arg:S>AB\nA>a str:1S>AB

		String[] string = addPoint.add(arg);
		char[] c = ter.getNonTerminal(arg);

		int i = (int) (str.charAt(0) - 48);

		strBuf[m].append(str);

		if (!((i + 2) == str.length())
				&& ter.isNonTerminal(str.charAt(i + 2), c)) {
			label1: for (int j = 0; j < string.length; j++) {

				// continue label1;

				if ((!string[j].equals("")) && string[j].charAt(0) == '1'
						&& string[j].charAt(1) == str.charAt(i + 2)) {
					for (int n = 0; n <= m; n++) {
						if (string[j].equals(strBuf[n].toString()))
							continue label1;
					}

					if (m < strBuf.length - 1) {

						m++;
						project(string[j], arg);

					}
				}
			}
		}

	}

	public StringBuffer[][] Lr0(String s) {
		String[] string = addPoint.add(s);

		StringBuffer[][] I = new StringBuffer[100][50];
		for (int a = 0; a < 100; a++) {

			for (int b = 0; b < 50; b++) {

				I[a][b] = new StringBuffer();
			}
		}
		for (int j = 0; j < string.length; j++) {
			if (!string[j].equals("")) {
				LR0 lr = new LR0();
				lr.project(string[j], s);
				I[j] = lr.strBuf;
			}
		}

		for (int j = 1; j < 100; j++) {
			if (!I[j][0].toString().equals("")
					&& I[j][0].toString().charAt(0) == '1') {
				for (int i = 0; i < 50; i++)
					I[j][i].delete(0, I[j][i].length());
			}
		}

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {

				if (!I[i][0].toString().equals("")
						&& !I[j][0].toString().equals("") && i != j) {
					int a = (int) (I[i][0].toString().charAt(0)) - 48;

					if (I[i][0].toString().charAt(0) == I[j][0].toString()
							.charAt(0)
							&& I[i][0].toString().charAt(a + 1) == I[j][0]
									.toString().charAt(a + 1)) {

						int n = 0, k = 0;
						do {

							if (!I[i][n].toString().equals("")) {

								if (I[i][n].toString().equals(
										I[j][k].toString())) {

									I[j][k].delete(0, I[j][k].length());
									k++;
								}
								n++;
							} else {
								I[i][n].append(I[j][k].toString());
								I[j][k].delete(0, I[j][k].length());
								n++;
								k++;
							}
						} while (!I[j][k].toString().equals(""));
					}
				}
			}
		}

		return I;
	}

	public static void main(String[] args) {
		LR0 lr = new LR0();
		int k = 0;
		String s = "O>S S>AB A>a B>b";
		String str = "1S>ABCD";
		StringBuffer[][] I0 = lr.Lr0(s);
		for (int i = 0; i < I0.length; i++) {
			if (!I0[i][0].toString().equals("")) {

				System.out.println("I" + k + ":");
				k++;
				for (int j = 0; j < I0[i].length; j++)
					if (!I0[i][j].toString().equals("")) {

						System.out.println(I0[i][j].toString());

					}
				System.out.println();
			}
		}
	}
}