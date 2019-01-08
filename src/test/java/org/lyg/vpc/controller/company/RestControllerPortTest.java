//package org.lyg.vpc.controller.company;
//import org.junit.After; 
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.lyg.mainInterface.MainApplication;
//import org.mockito.MockitoAnnotations;
//
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
// 
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// 
//public class RestControllerPortTest {
//	private MockMvc mockMvc;
////	@Autowired
////	private RestControllerPort restControllerPort;
//	@Before
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//	//	this.mockMvc = MockMvcBuilders.standaloneSetup(RestControllerPortImpl).build();
//	}
//	
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void startResults() throws Exception {
//		mockMvc.perform(get("/aa?aa={a}",1))
//		.andDo(print())
//		.andExpect(status().isOk());
//	}
//
//	@Test
//	public void startResultsBb() {
//	}
//}