#Makefile
#Kevin Lee
#1480757
#pa4
#Finds Roots
#simple Makefile for the Roots program
Roots: Roots.class
	echo Main-class: Roots > Manifest
	jar cvfm Roots Manifest Roots.class
	rm Manifest
	chmod u+x Roots
Roots.class: Roots.java
	javac -Xlint Roots.java
clean:
	rm –f Roots.class Roots
submit: 
	submit cmps011-pt.w16 pa4 Makefile Roots.java
Check:
	ls  /afs/cats.ucsc.edu/class/cmps011-pt.w16/pa4/kemalee
