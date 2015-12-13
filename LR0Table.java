public class LR0Table {
	public String[][] lr0Table(String arg) {
		Transform trf = new Transform();
		TerminalAndNon teran = new TerminalAndNon();
		LR0 lr0 = new LR0();

		StringBuffer tan = new StringBuffer();
		String[][] lr0t = new String[100][30];
		char[] ter1 = teran.getTerminal(arg);

		String ter = new String(ter1);
		int Inum = 0, shen = 0;
		StringBuffer[][] lr00 = lr0.Lr0(arg);
		StringBuffer[][] lr000 = new StringBuffer[100][50];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 50; j++) {
				lr000[i][j] = new StringBuffer();
			}
		}
		String[] trans = trf.transfer(arg);

		for (int i = 0; i < lr00.length; i++) {
			if (!lr00[i][0].toString().equals("")) {
				lr000[Inum] = lr00[i];
				Inum++;
			}
		}

		for (int i = 0; i < ter.length(); i++) {
			if (ter.charAt(i) != ' ')
				tan.append(ter.charAt(i));
		}
		label1: for (int i = 0; i < arg.length(); i++) {
			for (int j = 0; j < ter.length(); j++) {
				if (arg.charAt(i) == ter.charAt(j)) {
					continue label1;
				}

			}
			for (int j = 0; j < tan.length(); j++) {
				if (arg.charAt(i) == tan.charAt(j)) {
					continue label1;
				}
			}
			if (arg.charAt(i) != '>' && arg.charAt(i) != ' '
					&& arg.charAt(i) != '\n') {
				tan.append(arg.charAt(i));
				shen++;
			}
		}

		for (int k = 0; k < lr000.length; k++) {
			if (!lr000[k][0].toString().equals("")) {

				if (((int) (lr000[k][0].charAt(0)) - 46) != lr000[k][0]
						.length()) {

					for (int j = 0; j < tan.length(); j++) {
						la: for (int i = 0; i < lr000.length; i++) {
							if (!lr000[i][0].toString().equals("")
									&& (int) lr000[i][0].charAt(0) != lr000[i][0]
											.length()) {

								for (int b = 0; b < lr000[i].length; b++) {
									if (!lr000[k][b].toString().equals("")) {

										int m = (int) (lr000[k][b].charAt(0)) - 48;
										if ((m + 2) != lr000[k][b].length()) {

											if (tan.charAt(j) != '\u0000') {

												StringBuffer strbuf = new StringBuffer();
												strbuf.append(m + 1);
												for (int a = 1; a < lr000[k][b]
														.length(); a++) {

													strbuf.append(lr000[k][b]
															.charAt(a));
												}

												if ((lr000[k][b].charAt(m + 2) == tan
														.charAt(j))
														&& (j < tan.length()
																- shen)
														&& lr000[i][0]
																.toString()
																.equals(strbuf
																		.toString())) {
													lr0t[k][j] = ("S" + i);

												} else if (lr000[k][b]
														.charAt(m + 2) == tan
														.charAt(j)
														&& (j >= tan.length()
																- shen)
														&& lr000[i][0]
																.toString()
																.equals(strbuf
																		.toString())) {
													lr0t[k][j] = (i + "");

												}
											}
										}
									}
								}
							}
						}

					}

				}

				else {
					String stbuf = lr000[k][0].delete(0, 1).toString();
					for (int h = 0; h < trans.length; h++) {
						if (h == 0 && trans[h].equals(stbuf)) {
							lr0t[k][tan.length() - shen - 1] = "acc";
						} else if (trans[h].equals(stbuf)) {
							for (int g = 0; g < tan.length() - shen; g++) {
								lr0t[k][g] = ("r" + h);
							}
						}

					}
				}

			}
		}
		lr0t[99][29] = tan.length() + "";
		lr0t[99][28] = Inum + "";
		for (int i = 0; i < tan.length(); i++) {
			if (tan.charAt(i) == '\u0000')
				lr0t[98][i + 1] = "#";
			else
				lr0t[98][i + 1] = tan.charAt(i) + "";
		}

		return lr0t;

	}

	public static void main(String[] args) {
		LR0Table lrt = new LR0Table();
		String s = "O>S S>AB A>a B>b";
		String[][] lr0table = lrt.lr0Table(s);
		for (int i = 0; i < Integer.parseInt(lr0table[99][29]) + 1; i++) {
			System.out.print(lr0table[98][i] + "\t");
		}
		System.out.println();
		for (int i = 0; i < Integer.parseInt(lr0table[99][28]); i++) {
			System.out.print(i + "\t");
			for (int j = 0; j < Integer.parseInt(lr0table[99][29]); j++) {
				if (lr0table[i][j] == (null))
					System.out.print("\t");
				else
					System.out.print(lr0table[i][j] + "\t");

			}
			System.out.println();
		}
	}
}