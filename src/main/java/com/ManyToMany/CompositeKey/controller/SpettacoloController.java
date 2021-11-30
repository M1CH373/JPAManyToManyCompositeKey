package com.ManyToMany.CompositeKey.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ManyToMany.CompositeKey.dto.SpettacoloDto;
import com.ManyToMany.CompositeKey.entity.Spettacolo;
import com.ManyToMany.CompositeKey.service.SpettacoloService;

@RestController
@RequestMapping("/spettacolo")
public class SpettacoloController {

	@Autowired
	private SpettacoloService spettacoloService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<SpettacoloDto> spettacolo = spettacoloService.getAll().stream()
				.map(SpettacoloDto::toDto)
				.toList();
		return new ResponseEntity<>(spettacolo, HttpStatus.OK);
	}
	
	@GetMapping("/data")
	public ResponseEntity<?> getByData(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFirst , @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataLast){
		List<SpettacoloDto> spettacolo = spettacoloService.findByData(dataFirst, dataLast).stream()
				.map(SpettacoloDto::toDto)
				.toList();
		return new ResponseEntity<>(spettacolo, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> edit(@RequestBody SpettacoloDto spettacoloDto){
		Spettacolo spettacolo = SpettacoloDto.toEntity(spettacoloDto);
		spettacolo = spettacoloService.edit(spettacolo);
		SpettacoloDto sDto = SpettacoloDto.toDto(spettacolo);
		return new ResponseEntity<>(sDto, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> add(@RequestBody SpettacoloDto spettacoloDto){
		Spettacolo spettacolo = SpettacoloDto.toEntity(spettacoloDto);
		spettacolo = spettacoloService.add(spettacolo);
		SpettacoloDto sDto = SpettacoloDto.toDto(spettacolo);
		return new ResponseEntity<>(sDto, HttpStatus.OK);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		return new ResponseEntity<>(spettacoloService.delete(id), HttpStatus.OK);
	}
}

