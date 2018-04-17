package threads;

import src.Player;

public class Match extends Thread {
	private Player player1;
	private Player player2;
	private int[] points = new int[2];
	private int[] sets = new int[2];

	public Match(Player player1, Player player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
	}



	@Override
	public void run() {
	}
}
