program test;

var
    r,s: real;
    i,j: integer;
begin
   i := -1;
   i := abs(i);

   r := -3.0;
   r := abs(r);

   writeln(i);
   writeln(r);

   i := round(r);
   j := trunc(r);

   writeln(i);
   writeln(j);
end.