@.str.0 = constant [4 x i8] c"%d\0A\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca i32
	store i32 0, i32* %0
	%1 = mul i32 4, -1
	store i32 %1, i32* %0
	call void @test(i32* %0)
	%2 = load i32, i32* %0
	%3 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i64 0, i64 0
	%4 = call i32 (i8*, ...) @printf(i8* %3, i32 %2)
	ret i32 0
}

define void @test(i32*) {
	begin:
	%1 = alloca i32*
	store i32* %0, i32** %1
	%2 = load i32*, i32** %1
	%3 = load i32, i32* %2
	%4 = add i32 %3, 1
	store i32 %4, i32* %2
	ret void
}

