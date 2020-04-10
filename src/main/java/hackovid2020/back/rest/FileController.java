package hackovid2020.back.rest;

import hackovid2020.back.dto.file.FileRequest;
import hackovid2020.back.dto.file.FileResponse;
import hackovid2020.back.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/file")
@Api(value = "file", description = "File management", tags = { "File" })
public class FileController {
	
	
	@Autowired
	private FileService fileService;
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Creates a new file.")
	@Transactional
	public FileResponse createFile(FileRequest request) {
		return FileResponse.ofFile(fileService.saveFile(request.toFile()));
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Get a file.")
	@Transactional
	public FileResponse getFile(@PathVariable("id") Long id) {
		return FileResponse.ofFile(fileService.findFileById(id));
	}
	
	@PutMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Update a file.")
	@Transactional
	public FileResponse updateFile(@PathVariable("id") Long id, FileRequest request) {
		return FileResponse.ofFile(fileService.updateFile(id, request.getName(),
				request.getFileType(), request.getUrl()));
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Delete a file.")
	@Transactional
	public HttpStatus deleteFile(@PathVariable("id") Long id) {
		fileService.deleteFile(id);
		return HttpStatus.OK;
	}

}
