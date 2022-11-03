import com.google.gson.Gson;
import com.ssa.cms.controller.PatientWsControllers;
import com.ssa.cms.service.TableService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.util.UriComponentsBuilder;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath*:/spring/applicationContext*.xml")
//@ActiveProfiles("test")
//@WebAppConfiguration
public class FindDataBaseTables {

//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() throws Exception {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }

//    @Test
//    public void searchTables() throws Exception {
//
//        //CountDownLatch countDownLatch=new CountDownLatch(1);
//
////        String query="SELECT *  FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'rxdev_ComplianceRewardQA'";
//
//        URI uri = UriComponentsBuilder.fromUriString("/ComplianceRewards_war")
//                .pathSegment("testUnusedTable").build().encode().toUri();
//
//        mockMvc.perform(get(uri)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        //countDownLatch.await();
//
//    }

}
