@.str.0 = constant [4 x i8] c"%d\0A\00"
@.str.1 = constant [4 x i8] c"%d\0A\00"
@.str.2 = constant [4 x i8] c"%d\0A\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i64 0, i64 0
	%1 = call i32 (i8*, ...) @printf(i8* %0, i32 5)
	call void @test(i32 6)
	ret i32 0
}

define void @test(i32) {
	begin:
	%1 = alloca i32
	store i32 %0, i32* %1
	%2 = load i32, i32* %1
	%3 = add i32 %2, 1
	store i32 %3, i32* %1
	%4 = load i32, i32* %1
	%5 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i64 0, i64 0
	%6 = call i32 (i8*, ...) @printf(i8* %5, i32 %4)
	br label %7
	
	; <label>:7:
	%8 = load i32, i32* %1
	%9 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.2, i64 0, i64 0
	%10 = call i32 (i8*, ...) @printf(i8* %9, i32 %8)
	ret void
}

