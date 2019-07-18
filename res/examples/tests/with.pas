Program testw;  
type
    Coord = record
      X,Y : integer;
    end;
 
var
    S,T: Coord;
    X,Y: integer;
begin  
  S.X:= 1;
  S.Y:= 1;
  T.X:= 2;
  T.Y:= 2;

  X := 3;
  Y := 4;

  with S,T do
    writeLn (X,' ',Y);
end.