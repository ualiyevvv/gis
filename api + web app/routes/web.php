<?php

use App\Http\Controllers\ActivityController;
use App\Http\Controllers\UserController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/test', function () {
   return view('test');
});
Route::get('/', [ActivityController::class, 'index'])->name('main');
Route::post('/test', [UserController::class, 'store'])->name('test');
