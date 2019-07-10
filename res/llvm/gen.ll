@.str.0 = constant [4 x i8] c"%d\0A\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca i32
	store i32 0, i32* %0
	%1 = call i32 @test()
	store i32 %1, i32* %0
	%2 = load i32, i32* %0
	%3 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i32 0, i32 0
	%4 = call i32 (i8*, ...) @printf(i8* %3, i32 %2)
	%5 = call i32 @test()
	store i32 %5, i32* %0
	%6 = load i32, i32* %0
	%7 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i32 0, i32 0
	%8 = call i32 (i8*, ...) @printf(i8* %7, i32 %6)
	%9 = call i32 @test()
	store i32 %9, i32* %0
	%10 = load i32, i32* %0
	%11 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i32 0, i32 0
	%12 = call i32 (i8*, ...) @printf(i8* %11, i32 %10)
	ret i32 0
}

define i32 @test() {
	begin:
	%0 = alloca i32
	store i32 0, i32* %0
	%1 = alloca i32
	store i32 0, i32* %1
	%2 = call i32 @innerTest(i32* %1)
	store i32 %2, i32* %0
	%3 = load i32, i32* %0
	ret i32 %3
}

define i32 @innerTest(i32*) {
	begin:
	%1 = alloca i32*
	store i32* %0, i32** %1
	%2 = load i32*, i32** %1
	%3 = alloca i32
	store i32 0, i32* %3
	%4 = load i32, i32* %2
	%5 = add i32 %4, 1
	store i32 %5, i32* %3
	%6 = load i32, i32* %3
	ret i32 %6
}

