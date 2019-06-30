@.str.0 = constant [6 x i8] c"hallo\00"
@.str.1 = constant [4 x i8] c"%c\0A\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca [10 x i8]
	%1 = alloca [9 x i8]
	%2 = getelementptr inbounds [6 x i8], [6 x i8]* @.str.0, i64 0, i64 0
	store i8* %2, [9 x i8]* %1
	%3 = getelementptr inbounds [9 x i8], [9 x i8]* %1, i64 0, i64 0
	%4 = load i8, i8* %3
	%5 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i64 0, i64 0
	%6 = call i32 (i8*, ...) @printf(i8* %5, i8 %4)
	ret i32 0
}

