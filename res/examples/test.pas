program test;

var
    r,s: real;
    i: integer;
begin
   s := 2.0;
   r := sqrt((s + 1) * 3);
   writeln(r);

   i := 9;
   r := sqrt(i);
   writeln(r);
end.