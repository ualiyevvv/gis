<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use App\Models\Activity;
use Illuminate\Http\Request;

class ActivityController extends Controller
{
    public function index()
    {
        $activities = Activity::all();
        return response()->json($activities)->setStatusCode(200);

    }
    public function store(Request $request)
    {
        $error = null;
        try {
            Activity::create([
                'lat' => $request->lat,
                'lon' => (string)$request->lon,
            ]);
        }
        catch (\Exception $e) {
            $error = $e->getMessage();
        }

        return response()->json([
            "status" => true,
            "error" => $error,
        ]);
    }
}
