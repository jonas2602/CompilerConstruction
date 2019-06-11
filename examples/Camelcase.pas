program Camelcase;

var
    text, cc: string[100];
    c: char;
    i: integer;
    lastSpace: boolean;
    upper, lower: set of char;

begin
    upper := ['A'..'Z'];
    lower := ['a'..'z'];
    readln(text);
    lastSpace := true;
    cc := '';
    for i := 1 to Length(text) do
    begin
        c := text[i];
        if (c in lower) or (c in upper) then
        begin
            if (lastSpace) then { convert to uppercase }
            begin
                if (c in lower) then
                    c := chr(ord(c) - 32);
            end
            else { convert to lowercase }
                if (c in upper) then
                    c := chr(ord(c) + 32);
            cc := cc + c;
            lastSpace := false;
        end
        else
            lastSpace := true;
    end;
    writeln(cc);
end.
