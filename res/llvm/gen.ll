@.str.0 = constant [4 x i8] c"%d\0A\00"
@.str.1 = constant [4 x i8] c"%d\0A\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.0, i64 0, i64 0
	%1 = call i32 (i8*, ...) @printf(i8* %0, i32 5)
	call void @test(i32 6)
	br i1 1, label %2, label %5
	
	; <label>:2:
	%3 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i64 0, i64 0
	%4 = call i32 (i8*, ...) @printf(i8* %3, i32 5)
	br label %5
	
	; <label>:5:
	ret i32 0
}

define void @test(i32) {
	begin:
	%1 = alloca i32
	store i32 %0, i32* %1
	
	; <label>:2:
	%3 = load i32, i32* %1
	%4 = add i32 %3, 1
	store i32 %4, i32* %1
	br label %2
	%5 = load i32, i32* %1
	%6 = add i32 %5, 2
	store i32 %6, i32* %1
	ret void
}

