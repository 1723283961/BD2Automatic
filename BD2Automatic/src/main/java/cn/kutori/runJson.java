package cn.kutori;

import cn.kutori.GameUtils.Utils.ScriptRecordingTool.GameKeyRecorder;
import cn.kutori.dto.JsonEnum;
import cn.kutori.method.getJson;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class runJson {
    public static void main(String[] args) throws Exception {

        GameKeyRecorder gameKeyRecorder = new GameKeyRecorder();
        gameKeyRecorder.recordMap("RunningMap");



//        getJson getJson = new getJson("RunningMap");
//        getJson.runMap(JsonEnum.Map7_2.getPath());
    }
}
