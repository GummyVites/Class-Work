// Complex.java
// Kevin Lee
// 1480757
// pa6
// Imaginary number calculator.

class Complex{

   
   private double re;
   private double im;
   
  
   public static final Complex ONE = Complex.valueOf(1,0);
   public static final Complex ZERO = Complex.valueOf(0,0);
   public static final Complex I = Complex.valueOf(0,1);

  
   Complex(double a, double b){
      this.re = a;
      this.im = b;
   }

   Complex(double a){
      this.re = a;
      this.im = 0;
   }

    Complex(String s){
        double[] D = parseComplex(s);
        this.re = D[0];
        this.im = D[1];
    }

   
   Complex copy(){
      return new Complex(this.re, this.im);
   }
   
   
   Complex add(Complex z){
      return new Complex(this.re + z.re, this.im + z.im);
   }
   
   
   Complex negate(){
      return new Complex(this.re * -1, this.im * -1);
   }

   
   Complex sub(Complex z){
      return new Complex(this.re - z.re, this.im - z.im);
   }

   
   Complex mult(Complex z){
     double x = ((this.re*z.re) - (z.im*this.im));
     double y  = ((this.re*z.im) + (this.im*z.re));
       return new Complex (x, y);
   }

  
   Complex recip(){
     double x = (this.re/((Math.pow(this.re,2)) + Math.pow(this.im,2)));
     double y = (this.im*-1)/((Math.pow(this.re,2)) + Math.pow(this.im,2));
      return new Complex  (x, y);
   }

  
   Complex div(Complex z){
       if( z.equals(Complex.ZERO) ) {
         throw new NumberFormatException("Can't divide by zero");
      }
      else{
         double x = (( this.re * z.re ) + (this.im*z.im)) / ((Math.pow(z.re,2)) + (Math.pow(z.im,2)));
         double y = ((this.re * z.im) + (this.im*z.re)) / ((Math.pow(z.re,2)) + (Math.pow(z.im,2)));
      return new Complex (x, y);
   }
}

  
   Complex conj(){
      return new Complex(re, -im);
   }
   
   
   double Re(){
      return re;
   }

   
   double Im(){
      return im;
   }

   
   double abs(){
      
	double x = (Math.pow(this.re,2)) + (Math.pow(this.im,2));
	return Math.sqrt(x);
   }

   
   double arg(){
      return Math.atan2(im, re);
   }

   
    public String toString(){
      if ( this.im == 0 && this.re > 0){
            return this.re+" ";
        }else if (this.im == 0 && this.re < 0){
            return this.re+" ";
        }else if (this.im > 0 && this.re > 0){
            return this.re+"+"+this.im+"i";
        }else if (this.im < 0 && this.re < 0){
            return +this.re+"-"+(-this.im)+"i";
        }else if (this.im < 0 && this.re > 0){
            return +this.re+"-"+(-this.im)+"i";
        }else if ( this.im > 0 && this.re < 0){
            return this.re+"+"+this.im+"i";
        }
        return +this.im+"i";     
   }

   
   public boolean equals(Object obj){
      Complex a = (Complex) obj;
      return( this.re == a.re && this.im == a.im );
   }

  
   static Complex valueOf(double a, double b){
      return( new Complex(a, b) );
   }

   
   static Complex valueOf(double a){
      return( new Complex(a) );
   }

   
   static Complex valueOf(String s){
      return( new Complex(s) );
   }
   
   static double[] parseComplex(String str){
      double[] part = new double[2];
      String s = str.trim();
      String NUM = "(\\d+\\.\\d*|\\.?\\d+)";
      String SGN = "[+-]?";
      String OP =  "\\s*[+-]\\s*";
      String I =   "i";
      String OR =  "|";
      String REAL = SGN+NUM;
      String IMAG = SGN+NUM+"?"+I;
      String COMP = REAL+OR+
                    IMAG+OR+
                    REAL+OP+NUM+"?"+I;
      
      if( !s.matches(COMP) ){
         throw new NumberFormatException(
                   "Cannot parse input string \""+s+"\" as Complex");
      }
      s = s.replaceAll("\\s","");     
      if( s.matches(REAL) ){
         part[0] = Double.parseDouble(s);
         part[1] = 0;
      }else if( s.matches(SGN+I) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I, "1.0" ) );
      }else if( s.matches(IMAG) ){
         part[0] = 0;
         part[1] = Double.parseDouble( s.replace( I , "" ) );
      }else if( s.matches(REAL+OP+I) ){
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+")"+OP+".+" , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+")"+I , "$1"+"1.0" ) );
      }else{   
         part[0] = Double.parseDouble( s.replaceAll( "("+REAL+").+"  , "$1" ) );
         part[1] = Double.parseDouble( s.replaceAll( ".+("+OP+NUM+")"+I , "$1" ) );
      }
      return part;
   }
   
}
