@.str.0 = constant [4 x i8] c"%d\0A\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca i32
	store i32 0, i32* %0
	%1 = add i32 1, 4
	switch i32 %1, label %6 [ i32 1, label %2 i32 2, label %2 i32 3, label %4 ]
	
	; <label>:2:
	%3 = add i32 1, 0
	store i32 %3, i32* %0
	br label %8
	
	; <label>:4:
	%5 = add i32 2, 0
	store i32 %5, i32* %0
	br label %8
	
	; <label>:6:
	%7 = add i32 3, 0
	store i32 %7, i32* %0
	br label %8
	
	; <label>:8:
	%9 = load i32, i32* %0
	%10 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i64 0, i64 0
	%11 = call i32 (i8*, ...) @printf(i8* %10, i32 %9)
	ret i32 0
}

