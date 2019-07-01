@.str.0 = constant [5 x i8] c"test\00"
@.str.1 = constant [4 x i8] c"%c\0A\00"

declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture writeonly, i8* nocapture readonly, i64, i1)
declare i32 @printf(i8*, ...)

define i32 @main() {
	begin:
	%0 = alloca [5 x i8]
	%1 = getelementptr inbounds [5 x i8], [5 x i8]* @.str.0, i64 0, i64 0
	%2 = bitcast [5 x i8]* %0 to i8*
	call void @llvm.memcpy.p0i8.p0i8.i64(i8* %2, i8* %1, i64 1, i1 false)
	%3 = getelementptr inbounds [5 x i8], [5 x i8]* %0, i64 0, i64 0
	%4 = load i8, i8* %3
	%5 = getelementptr inbounds [4 x i8], [4 x i8]* @.str.1, i64 0, i64 0
	%6 = call i32 (i8*, ...) @printf(i8* %5, i8 %4)
	ret i32 0
}

