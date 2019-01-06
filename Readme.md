###String Calculator

- Support evaluation of parenthesized mathematical expressions

#### Input

The	first	line	of	the	input	gives	the	number	of	test	cases,	T.	T	test	cases
follow.	Each	consists	of	one	line	with	a	string	S.	S	represents	the
mathematical	expression	which	will	the	input	for	the	string	calculator
1	≤	T	≤	100
> 4
7+(6*5^2+3-4/2)
7+(67(56*2))
8*+7
(8*5/8)-(3/1)-5


#### Output

For	each	test	case,	output	one	line	containing	Case	#x:	y,	where	x	is	the
test	case	number	(starting	from	1)	and	y	is	either	INVALID	EXPRESSION	if
input	string	is	invalid	,	or	an	integer	representing	the	value	of	the
mathematical	expression	after	calculation.

>
Case	#1:	158
Case	#2:	INVALID	EXPRESSION
Case	#3:	INVALID	EXPRESSION
Case	#4:	-3

