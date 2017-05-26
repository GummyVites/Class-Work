#GCD.c
#Kevin Lee
#1480757
#lab8
# A Makefile for GCD.c

GCD: GCD.o
	gcc -o GCD GCD.o

GCD.o: GCD.c
	gcc -o -std=c99 GCD.c

clean:
	rm -f GCD.o

spotless:
	rm -f GCD.o GCD

submit: Makefile GCD.c
	submit cmps011-pt.w16 lab8 Makefile GCD.c
