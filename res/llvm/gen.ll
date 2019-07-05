
@.str.0 = constant [4 x i8] c"%d\0A\00"

@.global.0 = dso_local global i32 0

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	store i32 2, i32* @.global.0
	call void @test()
	%0 = load i32, i32* @.global.0
	%1 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i32 0, i32 0
	%2 = call i32 (i8*, ...) @printf(i8* %1, i32 %0)
	ret i32 0
}

define void @test() {
	begin:
	%0 = alloca i32
	store i32 0, i32* %0
	store i32 0, i32* %0
	call void @innertest(i32 1, i32* %0)
	%1 = load i32, i32* @.global.0
	%2 = load i32, i32* %0
	%3 = add i32 %1, %2
	store i32 %3, i32* @.global.0
	ret void
}

define void @innertest(i32, i32*) {
	begin:
	%2 = alloca i32
	store i32 %0, i32* %2
	%3 = alloca i32*
	store i32* %1, i32** %3
	%4 = load i32*, i32** %3
	%5 = load i32, i32* %2
	%6 = add i32 %5, 1
	store i32 %6, i32* %2
	%7 = load i32, i32* %4
	%8 = load i32, i32* %2
	%9 = add i32 %7, %8
	store i32 %9, i32* %4
	ret void
}

