var gulp = require('gulp'),
    browserSync = require('browser-sync').create();

gulp.task('serve', function () {
  browserSync.init({
      server: '.'
  });
  gulp.watch('./app/**/*.html').on('change', browserSync.reload);
  gulp.watch('./app/**/*.js').on('change', browserSync.reload);
  gulp.watch('./assets/css/*.css').on('change', browserSync.reload);
});
