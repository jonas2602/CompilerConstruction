@.str.0 = constant [16 x i8] c"%c%c%c%c%c%c%f\0A\00"
@.str.1 = constant [4 x i8] c"%d\0A\00"
@.str.2 = constant [4 x i8] c"%d\0A\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca [3 x [3 x i8]]
	%1 = alloca i8
	store i8 0, i8* %1
	%2 = alloca i32
	store i32 0, i32* %2
	%3 = alloca float
	store float 0.0, float* %3
	%4 = alloca i32
	store i32 0, i32* %4
	store i32 10, i32* %2
	store i8 120, i8* %1
	%5 = getelementptr inbounds [3 x [3 x i8]], [3 x [3 x i8]]* %0, i64 0, i64 0
	%6 = getelementptr inbounds [3 x i8], [3 x i8]* %5, i64 0, i64 0
	store i8 97, i8* %6
	%7 = getelementptr inbounds [3 x [3 x i8]], [3 x [3 x i8]]* %0, i64 0, i64 0
	%8 = getelementptr inbounds [3 x i8], [3 x i8]* %7, i64 0, i64 1
	store i8 98, i8* %8
	%9 = getelementptr inbounds [3 x [3 x i8]], [3 x [3 x i8]]* %0, i64 0, i64 0
	%10 = getelementptr inbounds [3 x i8], [3 x i8]* %9, i64 0, i64 2
	store i8 99, i8* %10
	%11 = load i32, i32* %2
	%12 = icmp slt i32 %11, 2
	br i1 %12, label %13, label %16
	
	; <label>:13:
	%14 = getelementptr inbounds [3 x [3 x i8]], [3 x [3 x i8]]* %0, i64 0, i64 0
	%15 = getelementptr inbounds [3 x i8], [3 x i8]* %14, i64 0, i64 0
	store i8 119, i8* %15
	br label %16
	
	; <label>:16:
	%17 = getelementptr inbounds [3 x [3 x i8]], [3 x [3 x i8]]* %0, i64 0, i64 0
	%18 = getelementptr inbounds [3 x i8], [3 x i8]* %17, i64 0, i64 0
	%19 = load i8, i8* %18
	store i8 %19, i8* %1
	%20 = getelementptr inbounds [3 x [3 x i8]], [3 x [3 x i8]]* %0, i64 0, i64 0
	%21 = getelementptr inbounds [3 x i8], [3 x i8]* %20, i64 0, i64 2
	%22 = load i8, i8* %1
	store i8 %22, i8* %21
	%23 = load i8, i8* %1
	%24 = getelementptr inbounds [3 x [3 x i8]], [3 x [3 x i8]]* %0, i64 0, i64 0
	%25 = getelementptr inbounds [3 x i8], [3 x i8]* %24, i64 0, i64 0
	%26 = load i8, i8* %25
	%27 = getelementptr inbounds [3 x [3 x i8]], [3 x [3 x i8]]* %0, i64 0, i64 0
	%28 = getelementptr inbounds [3 x i8], [3 x i8]* %27, i64 0, i64 1
	%29 = load i8, i8* %28
	%30 = getelementptr inbounds [3 x [3 x i8]], [3 x [3 x i8]]* %0, i64 0, i64 0
	%31 = getelementptr inbounds [3 x i8], [3 x i8]* %30, i64 0, i64 2
	%32 = load i8, i8* %31
	%33 = fpext float 1.0 to double
	%34 = getelementptr inbounds [16 x i8], [16 x i8]* @.str.0, i64 0, i64 0
	%35 = call i32 (i8*, ...) @printf(i8* %34, i8 %23, i8 32, i8 %26, i8 %29, i8 %32, i8 32, double %33)
	%36 = load i32, i32* %2
	%37 = call i32 @fact(i32 %36)
	call void @loops(i32 12)
	ret i32 0
}

define i32 @fact(i32) {
	begin:
	%1 = alloca i32
	store i32 %0, i32* %1
	%2 = alloca i32
	store i32 0, i32* %2
	%3 = load i32, i32* %1
	%4 = add i32 %3, 1
	%5 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i64 0, i64 0
	%6 = call i32 (i8*, ...) @printf(i8* %5, i32 %4)
	ret i32 0
}

define void @loops(i32) {
	begin:
	%1 = alloca i32
	store i32 %0, i32* %1
	%2 = alloca i32
	store i32 0, i32* %2
	store i32 0, i32* %2
	br label %3
	
	; <label>:3:
	%4 = load i32, i32* %2
	%5 = load i32, i32* %1
	%6 = icmp sle i32 %4, %5
	br i1 %6, label %7, label %12
	
	; <label>:7:
	%8 = load i32, i32* %2
	%9 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.2, i64 0, i64 0
	%10 = call i32 (i8*, ...) @printf(i8* %9, i32 %8)
	br label %11
	
	; <label>:11:
	br label %3
	
	; <label>:12:
	ret void
}

