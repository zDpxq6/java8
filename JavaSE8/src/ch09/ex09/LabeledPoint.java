package ch09.ex09;

import java.util.Objects;

public class LabeledPoint {
	private String label;
	private int x;
	private int y;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.label, this.x, this.y);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		LabeledPoint other = (LabeledPoint) obj;
		return Objects.equals(this.label, other.label) && this.x == other.x && this.y == other.y;
		// if (this.label == null) {
		// if (other.label != null)
		// return false;
		// } else if (!this.label.equals(other.label))
		// return false;
		// if (this.x != other.x)
		// return false;
		// if (this.y != other.y)
		// return false;
		// return true;
	}

}
