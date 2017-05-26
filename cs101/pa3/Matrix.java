//Kevin Lee
//1480757
//pa3
//cmps101
//Matrix.java

public class Matrix{

    private class Entry{

        int column;
        double value;

        Entry(int col, double val){
            column = col;
            value = val;
        }

        public boolean equals(Object entry){
            Entry temp = (Entry) entry;
            if(column == temp.column && value == temp.value)
                return true;
            else
                return false;
        }

        public String toString(){
            return ("("+column + ", " + value + ") " );
        }

    }
    int size;
    int NNZ;
    List[] row;

    Matrix(int n){
        size = n;
        NNZ = 0;
        row = new List[n+1];
        for(int i = 1; i<=n; i++){
            row[i] = new List();
        }
    }

    int getNNZ(){
        return this.NNZ;
    }

    int getSize(){
        return this.size;
    }

    public boolean equals(Object x){
        Matrix M = (Matrix) x;
        if(M.NNZ != NNZ){
            return false;
        }
        if(M.size != size){
            return false;
        }

        for(int i = 1; i<=size; i++){
            if(M.row[i].length() != this.row[i].length()){
                return false;
            }

            if(!(M.row[i].equals(row[i]))){
                return false;
            }
        }
        return true;
    }

    void makeZero(){
        for(int i = 1; i<=size; i++){
            row[i].clear();
        }
        NNZ = 0;
        size=0;
    }

    Matrix copy(){
        Matrix copyMatrix = new Matrix(this.getSize());
        for(int i=1; i<=size; i++){
            if(row[i].length() != 0){
                row[i].moveFront();
                while( row[i].index() >= 0 ){
                    Entry temp = (Entry) row[i].get();
                    copyMatrix.changeEntry(i, temp.column, temp.value);
                    row[i].moveNext();
                }
            }
        }
        copyMatrix.NNZ = NNZ;
        return copyMatrix;
    }

    void changeEntry(int i, int j, double x){

        if (i<1 || i>this.getSize()){
            throw new RuntimeException("changeEntry() error: invalid parameter in function");
        }
        if(j<1 || j>this.getSize()){
            throw new RuntimeException("changeEntry() error: invalid parameter in function");
        }

        Entry newEntry = new Entry(j, x);

        if(row[i].length() == 0 && x!=0){
            row[i].append(newEntry);
            NNZ++;
            return;
        }
        if(row[i].length() == 0 && x == 0){
            return;
        }
        row[i].moveFront();
        Entry rowEntry = (Entry) row[i].get();
        Entry front = (Entry) row[i].front();
        Entry back= (Entry) row[i].back();

        if(x == 0 && row[i].length()!=0){
            row[i].moveFront();
            while(row[i].index() >= 0){
                rowEntry = (Entry) row[i].get();
                if(rowEntry.column == j){
                    row[i].delete();
                    NNZ--;
                }else {
                    row[i].moveNext();
                }
            }
            return;
        }
        else{
            row[i].moveFront();
            if(back.column < j){
                row[i].append(newEntry);
                NNZ++;
                return;
            }
            if(front.column > j){
                row[i].prepend(newEntry);
                NNZ++;
                return;
            }
            while(row[i].index() >= 0){
                rowEntry = (Entry) row[i].get();
                if(rowEntry.column == j){
                    rowEntry.value = x;
                    return;
                }
                else if(rowEntry.column > j){
                    row[i].insertBefore(newEntry);
                    NNZ++;
                    return;
                }
                row[i].moveNext();
            }
        }
    }

    Matrix scalarMult(double x){
		Matrix A = new Matrix(size);
		for(int i =1; i<=this.size;i++){
			if(row[i].length() == 0){
			}	
			else{
				row[i].moveFront();
				Entry temp = (Entry) row[i].get();
				while(row[i].index() >= 0){ 
					temp = (Entry) row[i].get();
					temp = new Entry(temp.column,temp.value);
					temp.value = temp.value * x;
					A.row[i].append(temp);
					row[i].moveNext();
				}	
			}
		}
		A.NNZ= this.NNZ;
		return A;
	}

    Matrix add(Matrix M){
        if(getSize() != M.getSize()){
            throw new RuntimeException("add() called on Matricies with different sizes");
        }
        if(M == this) {
            return this.scalarMult(2.0);
        }
        Matrix newMatrix = new Matrix(size);
        Matrix A = new Matrix(getSize());

        for(int i = 1; i <= this.size; ++i) {
            A.row[i] = addSub(row[i], M.row[i],newMatrix.row[i],M.getSize(), true);
        }
        return A;
    }

    Matrix sub(Matrix M){
        if(getSize() != M.getSize()){
            throw new RuntimeException("sub() called on Matricies with different sizes");
        }
        if(M == this) {
            return new Matrix(getSize());
        }
        Matrix newMatrix = new Matrix(size);
        Matrix A = new Matrix(getSize());
        for(int i = 1; i <= this.size; ++i) {
            A.row[i] = addSub(row[i], M.row[i],newMatrix.row[i],M.getSize(), false);
        }
        return A;
    }

    Matrix transpose(){
        Matrix A = new Matrix(size);
        List r;
        for(int i =1; i<=this.size;i++){
            r = this.row[i];
            if(r.length() != 0){
                r.moveFront();

                int j = row[i].length();
                for(int k = 1; k<=j; k++){
                    Entry temp = (Entry) row[i].get();
                    temp = new Entry(temp.column, temp.value);
                    A.changeEntry( temp.column, i, temp.value);
                    row[i].moveNext();
                }
            }
        }
        A.NNZ = this.NNZ;
	return A;
    }


    Matrix mult(Matrix M){
        if(size != M.getSize()){
            throw new RuntimeException("Cannot multiply matrices of different size.");
        }
        Matrix multMatrix = new Matrix(size);
        Matrix transposed = M.transpose();
        for(int i=1; i<=size; i++){
            if(row[i].length() != 0){
                for(int j=1; j<=size; j++){
                    if(transposed.row[j].length() != 0)
                        multMatrix.changeEntry(i, j, dot(row[i], transposed.row[j]));
                }
            }
        }
        return multMatrix;
    }
                

    private static double dot(List P, List Q){
        double sum = 0;
        Entry A;
        Entry B;

        if( P.length() != 0 && Q.length() != 0 ) {
            P.moveFront();
            Q.moveFront();

            while( P.index() != 0 || Q.index() != 0 ){
                A = (Entry) P.get();
                B = (Entry) Q.get();
                if( A.column == B.column){
                    sum += A.value*B.value;
                    P.moveNext();
                    Q.moveNext();
                }
                else if(A.column < B.column){
                    P.moveNext();
                }
                else{
                    Q.moveNext();
                }
            }
        }
        return sum;
    }

    private List addSub(List P, List Q, List R, int M, boolean addOrSub) {
        Matrix newMatrix = new Matrix(size);
        Entry temp3;
        for(int i = 1; i<getSize(); i++){
 	    while(M>R.length){
            if( P.length() == 0 && Q.length() > 0 || P.length() == 0 && Q.length() == 0){
                Q.moveFront();
                while(Q.index() >= 0){
                    Entry temp = (Entry) Q.get();
                    if( addOrSub==false){
                        temp = new Entry(temp.column, -1.0 * temp.value);
                        R.append(temp);
                        newMatrix.NNZ++;
                        Q.moveNext();
                    }
                    if(addOrSub==true){
                        temp = new Entry(temp.column, temp.value);
                        R.append(temp);
                        newMatrix.NNZ++;
                        Q.moveNext();
                    }
                }
            }
            else if(P.length() > 0 && Q.length() == 0){
                P.moveFront();
                while(P.index() >= 0){
                    Entry temp = (Entry) P.get();
                    temp = new Entry(temp.column, temp.value);
                    R.append(temp);
                    newMatrix.NNZ++;
                    P.moveNext();
                }
            }
            if (addOrSub==true){
                if( P.length() > 0 && Q.length() > 0 ) {
                    P.moveFront(); Q.moveFront();
                    Entry back1 = (Entry) P.back();
                    Entry back2 = (Entry) Q.back();
                    Entry temp1 = (Entry) P.get();
                    Entry temp2 = (Entry) Q.get();
                    while( P.index() != -1 && Q.index() != -1 ){
                        temp1 = (Entry) P.get();
                        temp2 = (Entry) Q.get();

                        if( temp1.column == temp2.column){
                            temp3 = new Entry(temp1.column, temp1.value+temp2.value);
                            if(temp3.value != 0){
                                R.append(temp3);
                                newMatrix.NNZ++;
                            }
                            P.moveNext();
                            Q.moveNext();
                        }
                        else if(temp1.column < temp2.column){
                            temp3 = new Entry(temp1.column, temp1.value);
                            R.append(temp3);
                            newMatrix.NNZ++;
                            P.moveNext();
                        }
                        else if(temp1.column > temp2.column){
                            temp3 = new Entry(temp2.column, temp2.value);
                            R.append(temp3);
                            newMatrix.NNZ++;
                            Q.moveNext();
                        }
                    }
                    while(P.index() != -1){
                        temp1 = (Entry) P.get();
                        temp3 = new Entry( temp1.column, temp1.value);
                        R.append(temp3);
                        newMatrix.NNZ++;
                        P.moveNext();
                    }
                    while(Q.index() != -1 ){
                        temp2 = (Entry) Q.get();
                        temp3 = new Entry( temp2.column, temp2.value);
                        R.append(temp3);
                        newMatrix.NNZ++;
                        Q.moveNext();
                    }
                }
            }

            if(addOrSub==false){
                if( P.length() > 0 && Q.length() > 0 ) {
                    P.moveFront(); Q.moveFront();R.moveFront();
                    Entry temp1 = (Entry) P.get();
                    Entry temp2 = (Entry) Q.get();
                    while( P.index() != -1 && Q.index() != -1 ){
                        temp1 = (Entry) P.get();
                        temp2 = (Entry) Q.get();

                        if( temp1.column == temp2.column){

                            temp3 = new Entry(temp1.column, temp1.value - temp2.value);
                            if(temp3.value == 0){

                                newMatrix.NNZ--;
                                P.moveNext();
                                Q.moveNext();
                            }
                            else{
                                R.append(temp3);
                                newMatrix.NNZ++;

                                P.moveNext();
                                Q.moveNext();
                            }
                        }
                        else if(temp1.column < temp2.column){
                            temp3 = new Entry(temp1.column, temp1.value);
                            R.append(temp3);
                            newMatrix.NNZ++;
                            P.moveNext();
                        }
                        else if(temp1.column > temp2.column){
                            temp3 = new Entry(temp2.column, -temp2.value);
                            R.append(temp3);
                            newMatrix.NNZ++;
                            Q.moveNext();
                        }
                    }
                    while(P.index() != -1){
                        temp1 = (Entry) P.get();
                        temp3 = new Entry( temp1.column, temp1.value);
                        R.append(temp3);
                        newMatrix.NNZ++;
                        P.moveNext();
                    }
                    while(Q.index() != -1 ){
                        temp2 = (Entry) Q.get();
                        temp3 = new Entry( temp2.column, -temp2.value);
                        R.append(temp3);
                        newMatrix.NNZ++;
                        Q.moveNext();
                    }
                }
            }
	  }
        }
        return R;
    }

    public String toString(){
        Entry temp;
        String print = "";
        for(int i =1; i<=this.size;i++){
            row[i].moveFront();;
            if(row[i].length() > 0){
                print+= i + ": ";
            }
            while(row[i].length() > 0 && row[i].index() >= 0){
                temp = (Entry) row[i].get();
                print += temp.toString();
                row[i].moveNext();
            }
            if(row[i].length() > 0){
                print+= "\n";
            }

        }
        return print;
    }

}
