program strings;
var
    str: string[5];
    {chars: array[2..10] of char;}
begin
    str := 'test';
    writeln (str[0]);
end.

{program Hello;
var
    str: string[10] = 'hallo' ;
    charstr: array[2..10] of char = 'abc';
    chars: array[0..4] of char = ('a', 'b', 'c', 'd', 'e');

begin
  writeln (str);
end.}