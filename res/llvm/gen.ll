@.str.0 = constant [4 x i8] c"%c\0A\00"
@.str.1 = constant [4 x i8] c"%d\0A\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca i8
	store i8 0, i8* %0
	%1 = alloca i32
	store i32 0, i32* %1
	store i32 48, i32* %1
	%2 = load i32, i32* %1
	%3 = trunc i32 %2 to i8
	store i8 %3, i8* %0
	%4 = load i8, i8* %0
	%5 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i32 0, i32 0
	%6 = call i32 (i8*, ...) @printf(i8* %5, i8 %4)
	%7 = alloca i32
	store i32 1, i32* %7
	%8 = load i32, i32* %7
	store i32 %8, i32* %1
	%9 = load i32, i32* %1
	%10 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i32 0, i32 0
	%11 = call i32 (i8*, ...) @printf(i8* %10, i32 %9)
	%12 = zext i1 1 to i32
	store i32 %12, i32* %1
	%13 = load i32, i32* %1
	%14 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i32 0, i32 0
	%15 = call i32 (i8*, ...) @printf(i8* %14, i32 %13)
	%16 = load i8, i8* %0
	%17 = zext i8 %16 to i32
	store i32 %17, i32* %1
	%18 = load i32, i32* %1
	%19 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i32 0, i32 0
	%20 = call i32 (i8*, ...) @printf(i8* %19, i32 %18)
	ret i32 0
}

