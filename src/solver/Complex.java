package solver;

public class Complex {

    private double real;
    private double image;

    public Complex(double real, double image) {
        this.real = real;
        this.image = image;
    }

    public Complex(Complex complex) {
        real = complex.real;
        image = complex.image;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImage() {
        return image;
    }

    public void setImage(double image) {
        this.image = image;
    }

    public Complex add(Complex complex) {
        real += complex.real;
        image += complex.image;
        return this;
    }

    public Complex subtract(Complex complex) {
        real -= complex.real;
        image -= complex.image;
        return this;
    }

    public Complex multiply(Complex complex) {
        double real = this.real*complex.real - this.image*complex.image;
        double image = this.real*complex.image + complex.real*this.image;
        this.real = real;
        this.image = image;
        return this;
    }

    public Complex divide(Complex complex) {
        double real = (this.real * complex.real + this.image * complex.image) / (complex.real * complex.real + complex.image * complex.image);
        double image = (complex.real * this.image - this.real * complex.image) / (complex.real * complex.real + complex.image * complex.image);
        this.real = real;
        this.image = image;
        return this;
    }

    public boolean isZero() {
        return real == 0 && image == 0;
    }

    @Override
    public String toString() {
        String realPart = real == 0 && image == 0 ? "0": real == 0 ? "": real + "";
        String imagePart = image != 0 ? image + "i" : "";
        String sign = image <= 0 ? "": real == 0 ? "": "+";
        return realPart + sign + imagePart;
    }
}
