public class TerminalAndNon {
	Transform t = new Transform();

	public char[] getNonTerminal(String s) {
		String[] str = new String[50];
		int length = str.length;
		char[] VN = new char[length];
		char[] VN1 = new char[length];

		str = t.transfer(s);
		for (int i = 0; i < length; i++) {
			if (str[i].equals("")) {
			} else {
				VN[i] = str[i].charAt(0);
			}

		}
		VN1[0] = VN[0];
		int k = 1;
		for (int i = 1; i < VN.length; i++) {

			int flag = 0;
			for (int j = 0; j < i; j++) {
				if (VN[i] == VN[j]) {

					flag = 1;
				}
			}
			if (flag == 0) {
				VN1[k] = VN[i];
				k++;
			}
		}
		return VN1;
	}

	public char[] getTerminal(String s) {
		TerminalAndNon tt = new TerminalAndNon();
		char[] ch = new char[50];
		char[] terminal = new char[50];
		char[] terminal1 = new char[50];
		for (int i = 0; i < 50; i++)
			terminal1[i] = ' ';
		ch = tt.getNonTerminal(s);
		String[] str = t.transfer(s);
		int k = 0;
		for (int i = 0; i < str.length; i++) {
			if (!str[i].equals("")) {
				for (int j = 0; j < str[i].length(); j++) {
					if (!tt.isNonTerminal(str[i].charAt(j), ch)
							&& (str[i].charAt(j) != '>')) {
						if (str[i].charAt(j) != ' ') {
							terminal[k] = str[i].charAt(j);
						}
						k++;
					}
				}
			}
		}
		terminal1[0] = terminal[0];
		int ss = 1;
		for (int i = 1; i < terminal.length; i++) {

			int flag = 0;
			for (int j = 0; j < i; j++) {
				if (terminal[i] == terminal[j]) {

					flag = 1;
				}
			}
			if (flag == 0) {
				terminal1[ss] = terminal[i];
				ss++;
			}
		}
		return terminal1;
	}

	public boolean isNonTerminal(char c, char[] vn) {

		for (int i = 0; i < vn.length; i++) {
			if (vn[i] == c)
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		String s = "S>ABCD\nA>a\nA>e\nB>a\nC>c    D>d";
		TerminalAndNon ter = new TerminalAndNon();
		char[] vn = new char[50];
		vn = ter.getNonTerminal(s);
		for (int i = 0; i < vn.length; i++) {
			System.out.print(vn[i]);
		}
		char[] terminal = new char[50];
		terminal = ter.getTerminal(s);
		for (int i = 0; i < terminal.length; i++) {
			System.out.print(terminal[i]);
		}

		System.out.println(ter.isNonTerminal('A', vn));
		System.out.println(ter.isNonTerminal('T', vn));
		System.out.println(ter.isNonTerminal('E', vn));
		System.out.println(ter.isNonTerminal('D', vn));
		System.out.println(ter.isNonTerminal('a', vn));
	}

}