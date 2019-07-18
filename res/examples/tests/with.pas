Program testw;  
type
    Coord = record
      X,Y : integer;
    end;
 
var
    S,T: Coord;
    X,Y,Z: integer;
begin
    writeln('READY');

    S.X:= 1;
    S.Y:= 1;
    T.X:= 2;
    T.Y:= 2;

    X := 3;
    Y := 4;
    Z := 5;

    with S,T do
        writeln (X,' ',Y,' ',Z);

    with S do
    begin
        X := 12;
        Y := 11;
        Z := 10;
    end;

    writeln(S.X,' ',S.Y,' ',Z);
end.