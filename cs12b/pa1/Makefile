#Kevin Lee
#1480757
#12B
#Makefile
# A simple Makefile
Recursion: Recursion.class
	echo Main-class: Recursion > Manifest
	jar cvfm Recursion Manifest Recursion.class
	rm Manifest
	chmod +x Recursion
Recursion.class: Recursion.java
	javac -Xlint Recursion.java
clean:
	rm -f Recursion.class
