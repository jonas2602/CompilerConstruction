program strings;
var
    str: string[15];
    charptr: ^char;
    chars: array[2..10] of char;
begin
    writeln('READY');
    str := 'test';
    writeln(high(str));
    charptr := 'test123';
    str := str + 'abc' + 'd';
    writeln(length(str));
    writeln(str, ' my random string ', charptr);
end.

{program Hello;
var
    str: string[10] = 'hallo';
    charstr: array[2..10] of char = 'abc';
    chars: array[0..4] of char = ('a', 'b', 'c', 'd', 'e');

begin
    writeln('READY');
    writeln (str);
end.}