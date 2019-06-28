@.str.0 = constant [16 x i8] c"%c%c%c%c%c%c%f\0A\00"
@.str.1 = constant [4 x i8] c"%f\0A\00"

declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca [3 x i8]
	%1 = alloca i8
	store i8 0, i8* %1
	%2 = alloca i32
	store i32 0, i32* %2
	%3 = alloca float
	store float 0.0, float* %3
	store i8 120, i8* %1
	%4 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 0
	store i8 97, i8* %4
	%5 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 1
	store i8 98, i8* %5
	%6 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 2
	store i8 99, i8* %6
	br i1 1, label %7, label %9
	
	; <label>:7:
	%8 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 0
	store i8 119, i8* %8
	br label %9
	
	; <label>:9:
	%10 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 0
	%11 = load i8, i8* %10
	store i8 %11, i8* %1
	%12 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 2
	%13 = load i8, i8* %1
	store i8 %13, i8* %12
	%14 = load i8, i8* %1
	%15 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 0
	%16 = load i8, i8* %15
	%17 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 1
	%18 = load i8, i8* %17
	%19 = getelementptr inbounds [3 x i8], [3 x i8]* %0, i64 0, i64 2
	%20 = load i8, i8* %19
	%21 = fpext float 1.0 to double
	%22 = getelementptr inbounds [16 x i8], [16 x i8]* @.str.0, i64 0, i64 0
	%23 = call i32 (i8*, ...) @printf(i8* %22, i8 %14, i8 32, i8 %16, i8 %18, i8 %20, i8 32, double %21)
	%24 = load i32, i32* %2
	%25 = call i32 @fact(i32 %24)
	ret i32 0
}

define i32 @fact(i32) {
	begin:
	%1 = alloca i32
	store i32 %0, i32* %1
	%2 = alloca i32
	store i32 0, i32* %2
	%3 = load i32, i32* %1
	%4 = sitofp i32 %3 to float
	%5 = fadd float %4, 1.0
	%6 = fpext float %5 to double
	%7 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i64 0, i64 0
	%8 = call i32 (i8*, ...) @printf(i8* %7, double %6)
	ret i32 0
}

