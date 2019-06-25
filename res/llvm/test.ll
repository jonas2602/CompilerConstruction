; ModuleID = 'test.c'
source_filename = "test.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@myval = dso_local local_unnamed_addr global i32 0, align 4
@.str = private unnamed_addr constant [10 x i8] c"test = %d\00", align 1
@.str.1 = private unnamed_addr constant [5 x i8] c"John\00", align 1
@.str.2 = private unnamed_addr constant [22 x i8] c"%s ist %d Jahre alt.\0A\00", align 1
@.str.3 = private unnamed_addr constant [3 x i8] c"%d\00", align 1

; Function Attrs: nounwind uwtable
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = tail call i32 @test(i32 1, i32 2)
  %2 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([10 x i8], [10 x i8]* @.str, i64 0, i64 0), i32 %1)
  %3 = tail call float @addf(float 1.000000e+00)
  %4 = fptosi float %3 to i32
  store i32 %4, i32* @myval, align 4, !tbaa !2
  %5 = tail call float @addf(float 1.000000e+00)
  %6 = fptosi float %5 to i32
  store i32 %6, i32* @myval, align 4, !tbaa !2
  %7 = tail call float @addf(float 1.000000e+00)
  %8 = fptosi float %7 to i32
  store i32 %8, i32* @myval, align 4, !tbaa !2
  %9 = tail call float @addf(float 1.000000e+00)
  %10 = fptosi float %9 to i32
  store i32 %10, i32* @myval, align 4, !tbaa !2
  %11 = tail call float @addf(float 1.000000e+00)
  %12 = fptosi float %11 to i32
  store i32 %12, i32* @myval, align 4, !tbaa !2
  %13 = tail call float @addf(float 1.000000e+00)
  %14 = fptosi float %13 to i32
  store i32 %14, i32* @myval, align 4, !tbaa !2
  %15 = tail call float @addf(float 1.000000e+00)
  %16 = fptosi float %15 to i32
  store i32 %16, i32* @myval, align 4, !tbaa !2
  tail call void @none()
  %17 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([22 x i8], [22 x i8]* @.str.2, i64 0, i64 0), i8* getelementptr inbounds ([5 x i8], [5 x i8]* @.str.1, i64 0, i64 0), i32 27)
  ret i32 0
}

; Function Attrs: nounwind
declare dso_local i32 @printf(i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: nounwind uwtable
define dso_local i32 @test(i32, i32) local_unnamed_addr #0 {
  %3 = add nsw i32 %1, %0
  %4 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.3, i64 0, i64 0), i32 %3)
  ret i32 %3
}

; Function Attrs: norecurse nounwind readonly uwtable
define dso_local float @addf(float) local_unnamed_addr #2 {
  %2 = load i32, i32* @myval, align 4, !tbaa !2
  %3 = sitofp i32 %2 to float
  %4 = fadd float %3, %0
  ret float %4
}

; Function Attrs: norecurse nounwind uwtable
define dso_local void @none() local_unnamed_addr #3 {
  store i32 5, i32* @myval, align 4, !tbaa !2
  ret void
}

; Function Attrs: norecurse nounwind readonly uwtable
define dso_local i32 @addi(i32) local_unnamed_addr #2 {
  %2 = load i32, i32* @myval, align 4, !tbaa !2
  %3 = add nsw i32 %2, %0
  ret i32 %3
}

; Function Attrs: norecurse nounwind readnone uwtable
define dso_local float @testCast(i32) local_unnamed_addr #4 {
  %2 = sitofp i32 %0 to float
  ret float %2
}

; Function Attrs: norecurse nounwind readnone uwtable
define dso_local i32 @check(i32) local_unnamed_addr #4 {
  %2 = icmp slt i32 %0, 1
  %3 = sdiv i32 %0, 2
  %4 = zext i1 %2 to i32
  %5 = add nsw i32 %3, %4
  ret i32 %5
}

attributes #0 = { nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { norecurse nounwind readonly uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { norecurse nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { norecurse nounwind readnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 7.0.0-3~ubuntu0.18.04.1 (tags/RELEASE_700/final)"}
!2 = !{!3, !3, i64 0}
!3 = !{!"int", !4, i64 0}
!4 = !{!"omnipotent char", !5, i64 0}
!5 = !{!"Simple C/C++ TBAA"}
