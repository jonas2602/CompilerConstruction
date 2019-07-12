program TestProg(output);
    type
        PList = ^TList;
        TList = record
            num : integer;
            next: PList;
        end;

    var
        myList  : PList;
        listPtr : PList;
        nextPtr : PList;
begin
    writeln('READY');
    (* Construct list 42 -> 99 -> 12 -| *)

    new (myList);
    new (myList^.next);
    new (myList^.next^.next);
    myList^.next^.next^.next := nil;

    myList^.num := 42;
    myList^.next^.num := 99;
    myList^.next^.next^.num := 12;

    (* Traverse list *)

    listPtr := myList;
    while listPtr <> nil do
    begin
        writeln (listPtr^.num);
        listPtr := listPtr^.next;
    end;

    (* Clear list *)

    listPtr := myList;
    while listPtr <> nil do
    begin
        nextPtr := listPtr^.next;
        dispose (listPtr);
        listPtr := nextPtr;
    end;
    myList := nil;
end.