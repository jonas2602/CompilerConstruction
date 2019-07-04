@.str.0 = constant [4 x i8] c"%d\0A\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca i32
	%1 = alloca i32
	store i32 6, i32* %1
	%2 = load i32, i32* %1
	store i32 %2, i32* %0
	%3 = load i32, i32* %0
	%4 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i64 0, i64 0
	%5 = call i32 (i8*, ...) @printf(i8* %4, i32 %3)
	ret i32 0
}

