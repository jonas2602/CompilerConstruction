
@.str.0 = constant [5 x i8] c"TEST\00"
@.str.1 = constant [4 x i8] c"%s\0A\00"


declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca i32*
	%1 = alloca i32
	store i32 0, i32* %1
	%2 = alloca i32*
	%3 = alloca i32
	store i32 0, i32* %3
	store i32* %1, i32** %2
	store null , i32** %0
	%4 = load i32*, i32** %2
	%5 = load i32*, i32** %0
	%6 = icmp eq i32* %4, %5
	br i1 %6, label %7, label %11
	
	; <label>:7:
	%8 = getelementptr inbounds [5 x i8], [5 x i8]* @.str.0, i32 0, i32 0
	%9 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i32 0, i32 0
	%10 = call i32 (i8*, ...) @printf(i8* %9, i8* %8)
	br label %11
	
	; <label>:11:
	ret i32 0
}

