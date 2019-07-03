program types;
type
    mystr = string[5];
var
    str: mystr;
    str2: mystr;
begin
    str := 'test';
    str2 := str;
    writeln (str2);
end.