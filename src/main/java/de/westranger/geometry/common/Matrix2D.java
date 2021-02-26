package de.westranger.geometry.common;

import java.util.Arrays;

public final class Matrix2D {
    protected final double[][] data;

    public Matrix2D(final double a11, final double a12, final double a21, final double a22) {
        this.data = new double[][]{{a11, a12}, {a21, a22}};
    }

    public Matrix2D(final double[][] data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Matrix data must not be null");
        }

        if (data.length != 2) {
            throw new IllegalArgumentException("Matrix x dimension must be 2 but was " + data.length);
        }

        for (double[] tmp : data) {
            if (tmp.length != 2) {
                throw new IllegalArgumentException("Matrix y[0] dimension must be 2 but was " + data[0].length);
            }
        }

        this.data = new double[2][2];
        for (int i = 0; i < 2; i++) {
            System.arraycopy(this.data[i], 0, data[i], 0, 2);
        }
    }

    public double getValue(final int x, final int y) {
        return this.data[x][y];
    }

    public Matrix2D getInverse() throws ArithmeticException {
        final double check = this.det();

        if (check < 1e-10) {
            throw new ArithmeticException("matrix cannot be inverted");
        }

        final double[][] matrixParams = {{this.data[1][1], -this.data[0][1]}, {-this.data[1][0], this.data[0][0]}};
        final Matrix2D tmp = new Matrix2D(matrixParams);
        return tmp.multiply(1.0 / check);
    }

    public Matrix2D multiply(double scalar) {
        final double[][] matrixParams = {{this.data[0][0] * scalar, this.data[0][1] * scalar}, {this.data[1][0] * scalar, this.data[1][1] * scalar}};
        return new Matrix2D(matrixParams);
    }

    public Matrix2D multiply(Matrix2D matrix) {
        throw new RuntimeException("not yet implemented");
        //return null;
    }

    public Vector2D multiply(Vector2D vector) {
        return new Vector2D(this.data[0][0] * vector.getX() + this.data[0][1] * vector.getY(), this.data[1][0] * vector.getX() + this.data[1][1] * vector.getY());
    }

    public Matrix2D transpose() {
        final double[][] matrixParams = {{this.data[0][0], this.data[1][0]}, {this.data[0][1], this.data[1][1]}};
        return new Matrix2D(matrixParams);
    }

    public double det() {
        return this.data[0][0] * this.data[1][1] - this.data[0][1] * this.data[1][0];
    }
}
