import java.util.*;

public class ReyNegro242C {
	static class Celda {
		final int a;
		final int b;

		Celda(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public boolean equals(Object other) {
			if (other == this) {
				return true;
			}
			if (!(other instanceof Celda)) {
				return false;
			}
			Celda cell = (Celda) other;
			return cell.a == a && cell.b == b;
		}

		public int hashCode() {
			int hash = 17;
			hash = 31 * hash + a;
			hash = 31 * hash + b;
			return hash;
		}
	}

	static class Vertice {
		final Celda celda;
		final int s;

		Vertice(Celda nodo, int s) {
			this.celda = nodo;
			this.s = s;
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		final int x0 = s.nextInt();
		final int y0 = s.nextInt();
		final int x1 = s.nextInt();
		final int y1 = s.nextInt();
		final int n = s.nextInt();

		Set<Celda> perimitido = new HashSet<Celda>();

		for (int i = 0; i < n; ++i) {
			final int r = s.nextInt();
			final int a = s.nextInt();
			final int b = s.nextInt();
			for (int j = a; j <= b; ++j) {
				perimitido.add(new Celda(r, j));
			}
		}

		final int[] x = { 0, 0, -1, -1, -1, 1, 1, 1 };
		final int[] y = { 1, -1, -1, 1, 0, 1, -1, 0 };

		Queue<Vertice> queue = new LinkedList<Vertice>();
		Set<Celda> visitado = new HashSet<Celda>();

		final Celda principio = new Celda(x0, y0);
		final Celda fin = new Celda(x1, y1);

		visitado.add(principio);
		queue.offer(new Vertice(principio, 0));
		int res = -1;

		while (!queue.isEmpty()) {
			Vertice v = queue.poll();
			if (v.celda.equals(fin)) {
				res = v.s;
				break;
			}
			for (int i = 0; i < 8; ++i) {
				final Celda celda = new Celda(v.celda.a + x[i], v.celda.b + y[i]);
				if (perimitido.contains(celda) && visitado.add(celda)) {
					queue.offer(new Vertice(celda, v.s + 1));
				}
			}
		}
		System.out.println(res);
	}
}